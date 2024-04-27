package com.skilldistillery.jpavideostore.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "inventory_item")
public class InventoryItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Enumerated(EnumType.STRING)
	@Column(name = "media_condition")
	private MediaCondition mediaCondition;
	
	@Column(name = "last_update")
	private LocalDateTime lastUpdate;
	
	@ManyToOne
	@JoinColumn(name = "film_id")
	private Film film;

	@ManyToOne
	@JoinColumn(name = "store_id")
	private Store store;
	
	@OneToMany(mappedBy = "inventoryItem")
	private List<Rental> rentals;
	
	public InventoryItem() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public MediaCondition getMediaCondition() {
		return mediaCondition;
	}

	public void setMediaCondition(MediaCondition mediaCondition) {
		this.mediaCondition = mediaCondition;
	}

	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(LocalDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public List<Rental> getRentals() {
		return rentals;
	}

	public void setRentals(List<Rental> rentals) {
		this.rentals = rentals;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InventoryItem other = (InventoryItem) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("InventoryItem [id=").append(id).append(", mediaCondition=").append(mediaCondition)
				.append(", lastUpdate=").append(lastUpdate).append("]");
		return builder.toString();
	}
}
