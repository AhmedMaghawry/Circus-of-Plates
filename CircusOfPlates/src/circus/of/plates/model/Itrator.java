package circus.of.plates.model;


public interface Itrator {

    public boolean hasNext();
    public Shape next();
    public boolean hasPrev();
    public Shape prev();
    public Shape getCurrent();
    public Shape setFirst();
    public Shape setLast();
    public int getIndex();
}
