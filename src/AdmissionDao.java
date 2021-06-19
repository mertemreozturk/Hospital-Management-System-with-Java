

import java.util.ArrayList;
import java.util.Comparator;

public class AdmissionDao implements Dao{
    ArrayList<Admission> admissions;
    Admission examinationFromFile;
    String[] txt;

    public AdmissionDao() {
        this.admissions = new ArrayList<Admission>();
        ReadFromFile file = new ReadFromFile();
        this.txt = file.readFile("admission.txt");
        int flag = 0;
        int pID = 0;
        for(String lines:txt){
            String[] x = lines.split("\t");
            if (!x[0].equals("Inpatient") && !x[0].equals("Outpatient")){    // admissionID <tab> patientID
                flag = Integer.parseInt(x[0]);   // admissionID
                pID = Integer.parseInt(x[1]);    // patientID
            }else{ 
                String [] y = x[1].split(" ");    // add operation
                if ( x[0].equals("Inpatient")){
                    examinationFromFile = new Inpatient(flag,pID);   // according to numbers add new operation with decorator pattern
                }else{
                    examinationFromFile = new Outpatient(flag,pID);
                }
                admissions.add(adder(y,examinationFromFile));
            }
        }
    }

    @Override
    public Object getByID(int ID) {
        for (Admission admission : admissions) {
            if (ID == admission.admissionID()) {
                return admission;
            }
        }
        return null;
    }

    @Override
    public void deleteByID(int ID) {
        admissions.removeIf(a -> ID == a.patientID());
    }

    @Override
    public void add(Object obj) {
        admissions.add((Admission)obj);
    }

    @Override
    public ArrayList<Admission> getALL() {
        admissions.sort(Comparator.comparingInt(Admission::admissionID));  // sort by admissionID
        return admissions;
    }
    public Admission adder(String[] operations,Admission examination){  
        for(int i = 0; i < operations.length; i++){   // each iteration, add new operation with use decorator design pattern
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

}
