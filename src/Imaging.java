

public class Imaging extends AdmissionDecorator{

    public Imaging(Admission examination) {
        super(examination);
    }

    @Override
    public String printOperations() {
        return examination.printOperations()+"imaging ";
    }

    @Override
    public int cost() {
        return examination.cost()+10;    // cost for imaging:10
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
