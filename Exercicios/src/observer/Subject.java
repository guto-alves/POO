package observer;

public interface Subject {
	public abstract void notificar();

	public abstract void registrar(Observer o);
}
