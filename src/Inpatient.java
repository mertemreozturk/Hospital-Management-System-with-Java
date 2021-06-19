

public class Inpatient extends Examination{

    public Inpatient(int admissionID, int patientID) {
        super(admissionID, patientID);
    }

    @Override
    public String printOperations() {

        return super.printOperations()+"Inpatient\t";
    }

    @Override
    public int cost() {
        return super.cost()+10;   // cost for inpatient:10
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
