package com.vizuri.rules.domain;

import java.io.Serializable;
import java.util.List;

public class Roster implements Serializable {

	private static final long serialVersionUID = -6571292660178693269L;
	
	private List<Player> players;
	private int size;
	private boolean isSizeValid;
	
	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public boolean isSizeValid() {
		return isSizeValid;
	}
	public void setSizeValid(boolean isSizeValid) {
		this.isSizeValid = isSizeValid;
	}
	
	@Override
	public String toString() {
		return "Roster [players=" + players + ", size=" + size
				+ ", isSizeValid=" + isSizeValid + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isSizeValid ? 1231 : 1237);
		result = prime * result + ((players == null) ? 0 : players.hashCode());
		result = prime * result + size;
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
		Roster other = (Roster) obj;
		if (isSizeValid != other.isSizeValid)
			return false;
		if (players == null) {
			if (other.players != null)
				return false;
		} else if (!players.equals(other.players))
			return false;
		if (size != other.size)
			return false;
		return true;
	}
	
	
	
	
}
