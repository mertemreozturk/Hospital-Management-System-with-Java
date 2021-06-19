

public class Measurements extends AdmissionDecorator{

    public Measurements(Admission examination) {
        super(examination);
    }

    @Override
    public String printOperations() {
        return examination.printOperations()+"measurements ";
    }

    @Override
    public int cost() {
        return examination.cost()+5;   // cost for measurement:5
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
