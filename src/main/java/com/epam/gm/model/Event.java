package com.epam.gm.model;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.epam.gm.olgmaks.absractdao.annotation.*;

@Entity("event")
public class Event {
	@AutoGenerated
	@Column("id")
	private Integer id;
	@Column("name")
	private String name;
	@Column("description")
	private String description;
	@Column("date_from")
	private Date dateFrom;
	@Column("date_to")
	private Date dateTo;
	@Column("address_id")
	private Integer addressId;
	@Column("moderator_id")
	private Integer moderatorId;
	@Column("participants_limit")
	private Integer participants_limit;

	@ForeignKey("id")
	@OneToMany(field = "moderator_id", value = User.class)
	private User moderator;
	@Column("status")
	private String status;

	@ForeignKey("id")
	@OneToMany(field = "address_id", value = Address.class)
	private Address address;

	@Column("avatar_id")
	private Integer avatarId = 11;

	@ForeignKey
	@OneToMany(field = "avatar_id", value = Photo.class)
	private Photo avatar;
	
	@Column("created_on")
	private Date createdOn;	
	
	//gryn
	private Double points;

	//gryn
	private String tagString; 
	
	private List<String> tagList;
	
	public Integer getAvatarId() {
		return avatarId;
	}

	public void setAvatarId(Integer avatarId) {
		this.avatarId = avatarId;
	}

	public Photo getAvatar() {
		return avatar;
	}

	public void setAvatar(Photo avatar) {
		this.avatar = avatar;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	public Integer getModeratorId() {
		return moderatorId;
	}

	public void setModeratorId(Integer moderatorId) {
		this.moderatorId = moderatorId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getParticipants_limit() {
		return participants_limit;
	}

	public void setParticipants_limit(Integer participants_limit) {
		this.participants_limit = participants_limit;
	}

	public User getModerator() {
		return moderator;
	}

	public void setModerator(User moderator) {
		this.moderator = moderator;
	}
	
	public Double getPoints() {
		return points;
	}

	public void setPoints(Double points) {
		this.points = points;
	}
	
	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	
	public String getTagString() {
		return tagString;
	}

	public void setTagString(String tagString) {
		this.tagString = tagString;
	}
	

	public List<String> getTagList() {
		return tagList;
	}

	public void setTagList(List<String> tagList) {
		this.tagList = tagList;
	}

	//gryn
	public String getEventNameAndCity() {
		 StringBuilder res = new StringBuilder(name); 
		
		 if(getAddress() != null && getAddress().getCity() != null)
			 res.append(", ").append(getAddress().getCity().getName());
		 
		 return res.toString();
	 }
	
	//gryn
	public String getNameCityPoints() {
		return new StringBuilder(getEventNameAndCity()).append(", rate: ").append(Math.round(getPoints())).toString();
	}	
	
	
	
	//gryn  
	//Compare events by points in desc. order
//	public static final Comparator<Event> BY_POINTS = 
//			(first, second) -> {
//				return second.points.compareTo(first.points);
//			}; 
			
	public static final Comparator<Event> BY_POINTS = new Comparator<Event>() {
		@Override
		public int compare(Event first, Event second) {
			return second.points.compareTo(first.points);
		}
		
	};		
	
	public static final Comparator<Event> BY_CREATED_DATE = new Comparator<Event>() {
		@Override
		public int compare(Event first, Event second) {
			return second.createdOn.compareTo(first.createdOn);
		}
		
	};		
			
			

	@Override
	public String toString() {
		return "Event [id=" + id + ", name=" + name + ", description="
				+ description + ", dateFrom=" + dateFrom + ", dateTo=" + dateTo
				+ ", addressId=" + addressId + ", moderatorId=" + moderatorId
				+ ", participants_limit=" + participants_limit + ", moderator="
				+ moderator + ", status=" + status + ", address=" + address
				+ "]";
	}
	
	@Override
	public int hashCode() {
		if(id == null) return 0;
		
		return id.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		
		if(obj instanceof Event) {
			return id.equals(((Event) obj).getId());
		}
		
		return false;
	}
	
	public static void main(String[] args) {
		Event e1 = new Event();
		Event e2 = new Event();
		Event e3 = new Event();
		
		e1.setId(5);
		e2.setId(5);
		e3.setId(7);
		System.out.println(e1.equals(e2));
		System.out.println(e2.equals(e2));
		System.out.println(e2.equals(e1));
		System.out.println(e2.equals(e3));
		
		System.out.println(e3.equals(null));
		System.out.println(e3.equals(new User()));
	}

}
