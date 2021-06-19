

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class Controller{
    private String[] text;
    private PatientDao patientDao;
    private Admission examination;
    private AdmissionDao admissionDao;
    private ArrayList<String> output;

    public Controller(String data) {
        this.patientDao = new PatientDao();    // read patient.txt and add patient to list
        this.admissionDao = new AdmissionDao();   // read admission.txt and add operation to list
        this.output = new ArrayList<String>();
        ReadFromFile file = new ReadFromFile();    // read file
        this.text = file.readFile(data);  
    }

    public void control() {
        for(String line:text){   // each iteration one sentence from input.txt
            String[] x = line.split(" ",6);

            switch (x[0]){   
                case "AddPatient":    // x[1]:id,x[2]:name,x[3]:surname,x[4]:phonenumber,x[5]:address
                    patientDao.add(new Patient(Integer.parseInt(x[1]),x[2],x[3],x[4],"Address: "+x[5]));  
                    output.add("Patient"+" "+x[1]+" "+x[2]+" "+"added");  
                    break;
                case "RemovePatient":  // x[1]:id
                    output.add("Patient"+" "+x[1]+" "+((Patient)patientDao.getByID(Integer.parseInt(x[1]))).getName()+" "+"removed");
                    admissionDao.deleteByID(Integer.parseInt(x[1]));   // delete patient from admission.txt
                    patientDao.deleteByID(Integer.parseInt(x[1]));    // delete patient from patient.txt
                    break;
                case "CreateAdmission":   // x[1]:admissionID,x[2]:patientID
                    output.add("Admission "+x[1]+" created");
                    examination = new Examination(Integer.parseInt(x[1]),Integer.parseInt(x[2]));   // reference admission
                    admissionDao.add(examination);
                    break;
                case "AddExamination":   // x[2]:examinationtype,x[1]:admissionID
                    output.add(x[2]+" examination added to admission "+x[1]);
                    int patientID = ((Admission)admissionDao.getByID(Integer.parseInt(x[1]))).patientID();
                    if ( x[2].equals("Inpatient")){
                        examination = new Inpatient(Integer.parseInt(x[1]),patientID);
                    }else{
                        examination = new Outpatient(Integer.parseInt(x[1]),patientID);
                    }
                    admissionDao.add(adder(x,examination));
                    break;
                case "TotalCost":     // x[1]:admissionID
                    output.add("TotalCost for admission "+x[1]);
                    int total = 0;  
                    for ( Admission ad:admissionDao.getALL()){
                        if ( Integer.parseInt(x[1]) == ad.admissionID()){
                            if ( ad.cost() != 0){
                                total += ad.cost();   
                                output.add("\t"+ad.printOperations()+ad.cost()+"$");   // calculate cost each examination
                            }
                        }
                    }
                    output.add("\tTotal: "+total+"$");
                    break;
                default:
                    output.add("Patient List:");
                    for (Patient pp:sortByAlphabet(patientDao.getALL())){   // sort by alphabet and add patient to output list
                        output.add(pp.getId()+"\t"+pp.getName()+"\t"+pp.getSurname()
                                +"\t"+pp.getPhoneNumber()
                                +"\t"+pp.getAddress());
                    }
            }
        }
        convert();  // convert arrayList type
    }

    public void convert(){    // convert arrayList format 
        ArrayList<String> patientList = new ArrayList<String>();   // write to list from patients(arrayList which including patient information)
        ArrayList<String> admissionList = new ArrayList<String>();
        int flag = 0;
        for(Admission a:admissionDao.getALL()){
            if ( flag != a.admissionID()){   // process each new admissionID
                admissionList.add(a.admissionID()+"\t"+a.patientID());
                flag = a.admissionID();  // new admissionID and according to this id, add operation with this id
            }
            if (a.cost() != 0 ){   // if operation exist, we add operation  
                String str = a.printOperations();
                admissionList.add(str.substring(0,str.length()-1));
            }
        }
        for(Patient p:patientDao.getALL()){
            patientList.add(p.getId()+"\t"+p.getName()+" "+p.getSurname()+"\t"+
                p.getPhoneNumber()+"\t"+p.getAddress());
        }
        writeToFile(output, "output.txt");   // write output.txt
        writeToFile(patientList, "patient.txt");   // updated file
        writeToFile(admissionList, "admission.txt");  // updated file
    }
    
    public ArrayList<Patient> sortByAlphabet(ArrayList<Patient> arr){   // sort by alphabet list of patient for output.txt
        arr.sort((patientOne, patientTwo) -> patientOne.getName()
                .compareTo(patientTwo.getName()));
        return arr;
    }
    public Admission adder(String[] operations,Admission examination){
        for(int i = 3; i < operations.length; i++){  // each iteration with use decorator pattern add operation
            if ( operations[i].equals("imaging")){
                examination = new Imaging(examination);
            } else if(operations[i].equals("measurements")){
                examination = new Measurements(examination);
            }else if( operations[i].equals("tests")){
                examination = new Tests(examination);
            }else{
                examination = new DoctorVisit(examination);
            }
        }
        return examination;
    }
    public void writeToFile(ArrayList<String> lists,String fileName) {
        try {
            FileWriter fWriter = new FileWriter(fileName);   
            BufferedWriter writer = new BufferedWriter(fWriter);
            for (int i = 0; i < lists.size(); i++) {    // write to file according to filename
                if ( i != lists.size() - 1){
                    writer.write(lists.get(i)+"\n");
                }else{
                    writer.write(lists.get(i));
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
