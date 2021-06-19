

public abstract class AdmissionDecorator implements Admission{   // this class provide decorator pattern design
    protected Admission examination;
    public AdmissionDecorator(Admission examination) {
        super();
        this.examination = examination;
    }

}
