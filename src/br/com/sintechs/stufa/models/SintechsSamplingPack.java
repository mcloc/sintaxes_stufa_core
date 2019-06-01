package br.com.sintechs.stufa.models;

import java.util.ArrayList;
import java.util.List;

public class SintechsSamplingPack {
	
	private List<SintechsSampling> sampling_list = new ArrayList<SintechsSampling>();

	public List<SintechsSampling> getSampling_list() {
		return sampling_list;
	}

	public void setSampling_list(List<SintechsSampling> sampling_list) {
		this.sampling_list = sampling_list;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sampling_list == null) ? 0 : sampling_list.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SintechsSamplingPack other = (SintechsSamplingPack) obj;
		if (sampling_list == null) {
			if (other.sampling_list != null)
				return false;
		} else if (!sampling_list.equals(other.sampling_list))
			return false;
		return true;
	}

	
}
