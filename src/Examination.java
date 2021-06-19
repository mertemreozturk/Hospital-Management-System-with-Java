

public class Examination implements Admission{
    protected int admissionID;
    protected int patientID;

    public Examination(int admissionID, int patientID) {
        this.admissionID = admissionID;
        this.patientID = patientID;
    }

    @Override
    public String printOperations() {
        return "";
    }

    @Override
    public int cost() {
        return 0;
    }

    @Override
    public int admissionID() {    // admissionID of patient for each examination
        return admissionID;
    }

    @Override
    public int patientID() {    // patientID of patient for each examination
        return patientID;
    }


    public int getAdmissionID() {
        return admissionID;
    }

    public void setAdmissionID(int admissionID) {
        this.admissionID = admissionID;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }
}
