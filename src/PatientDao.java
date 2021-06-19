

import java.util.ArrayList;
import java.util.Comparator;

public class PatientDao implements Dao{
    ArrayList<Patient> patients;
    String[] text;

    public PatientDao() {
        this.patients = new ArrayList<Patient>();
        ReadFromFile file = new ReadFromFile();
        this.text = file.readFile("patient.txt");
        for(String txt:text){
            String[] x = txt.split("\t");
            String[] y = x[1].split(" ");  // split name and surname each other
            patients.add(new Patient(Integer.parseInt(x[0]),y[0],y[1],x[2],x[3]));
        }
    }

    @Override
    public Object getByID(int ID) {
        for ( Patient patient:patients){
            if ( ID == patient.getId()){
                return patient;
            }
        }
        return null;
    }

    @Override
    public void deleteByID(int ID) {
        patients.removeIf(a -> ID == a.getId());
    }

    @Override
    public void add(Object obj) {
        patients.add((Patient)obj);
    }

    @Override
    public ArrayList<Patient> getALL() {
        patients.sort(Comparator.comparingInt(Patient::getId)); // sort by patient
        return patients;
    }
}
