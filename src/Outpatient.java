

public class Outpatient extends Examination{

    public Outpatient(int admissionID, int patientID) {
        super(admissionID, patientID);
    }

    @Override
    public String printOperations() {

        return super.printOperations()+"Outpatient\t";
    }

    @Override
    public int cost() {
        return super.cost()+15;   // cost for outpatient:15
    }

    @Override
    public int admissionID() {

        return super.admissionID();
    }

    @Override
    public int patientID() {
        return super.patientID();
    }
}