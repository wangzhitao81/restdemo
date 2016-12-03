package demo.ralph.dto;

public class RestEntity<T> extends BaseDto{
	private T data;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
