

public class DoctorVisit extends AdmissionDecorator{
	
    public DoctorVisit(Admission examination) {

        super(examination);
    }

    @Override
    public String printOperations() {
        return examination.printOperations()+"doctorvisit ";
    }

    @Override
    public int cost() {
        return examination.cost()+15;   // cost for doctorvisit:15
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
