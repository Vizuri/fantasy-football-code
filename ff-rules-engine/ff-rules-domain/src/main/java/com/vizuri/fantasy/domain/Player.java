/**
 * 
 */
package com.vizuri.fantasy.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author amirge
 *
 */
public class Player implements Serializable {

	private static final long serialVersionUID = -3605975122593164477L;
	
	private Long id;
	private String name;
	private String proTeamNickname;
	private Integer number;
    private String position;
    private PlayerStatus status;
    private Boolean doNotCut;
    
    private Integer positionRankingOrder = 2000;
    private BigDecimal positionRankingWeight = BigDecimal.ZERO;
    
    private Integer overallRankingOrder = 2000;
    private BigDecimal overallRankingWeight = BigDecimal.ZERO;
    
    private Boolean active = true;
	
    public Player(){
    }
    
    public Player(Long id){
    	this.id = id;
    }

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProTeamNickname() {
		return this.proTeamNickname;
	}
	public void setProTeamNickname(String proTeamNickname) {
		this.proTeamNickname = proTeamNickname;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public PlayerStatus getStatus() {
		return status;
	}
	public void setStatus(PlayerStatus status) {
		this.status = status;
	}
	public Boolean getDoNotCut() {
		return doNotCut;
	}
	public void setDoNotCut(Boolean doNotCut) {
		this.doNotCut = doNotCut;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}	
	public Integer getPositionRankingOrder() {
		return positionRankingOrder;
	}
	public void setPositionRankingOrder(Integer positionRankingOrder) {
		this.positionRankingOrder = positionRankingOrder;
	}
	public BigDecimal getPositionRankingWeight() {
		return positionRankingWeight;
	}
	public void setPositionRankingWeight(BigDecimal positionRankingWeight) {
		this.positionRankingWeight = positionRankingWeight;
	}
	public Integer getOverallRankingOrder() {
		return overallRankingOrder;
	}
	public void setOverallRankingOrder(Integer overallRankingOrder) {
		this.overallRankingOrder = overallRankingOrder;
	}
	public BigDecimal getOverallRankingWeight() {
		return overallRankingWeight;
	}
	public void setOverallRankingWeight(BigDecimal overallRankingWeight) {
		this.overallRankingWeight = overallRankingWeight;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Player [id=" + id + ", name=" + name + ", proTeamNickname=" + proTeamNickname
				+ ", number=" + number + ", position=" + position + ", status="
				+ status + ", doNotCut=" + doNotCut + ", positionRankingOrder="
				+ positionRankingOrder + ", positionRankingWeight="
				+ positionRankingWeight + ", overallRankingOrder="
				+ overallRankingOrder + ", overallRankingWeight="
				+ overallRankingWeight + ", active=" + active + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Player other = (Player) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}
