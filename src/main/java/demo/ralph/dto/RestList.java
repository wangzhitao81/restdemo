package demo.ralph.dto;

import java.util.List;

public class RestList<T> extends BaseDto {
	private List<T> data;

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	
}
