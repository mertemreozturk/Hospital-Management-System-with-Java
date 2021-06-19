

public class Tests extends AdmissionDecorator{

    public Tests(Admission examination) {
        super(examination);
    }

    @Override
    public String printOperations() {
        return examination.printOperations()+"tests ";
    }

    @Override
    public int cost() {
        return examination.cost()+7;  // cost for test:7
    }

    @Override
    public int admissionID() {
        return examination.admissionID();
    }

    @Override
    public int patientID() {
        return examination.patientID();
    }
}
