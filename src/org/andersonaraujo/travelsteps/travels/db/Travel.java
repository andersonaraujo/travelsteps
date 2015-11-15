package org.andersonaraujo.travelsteps.travels.db;

import java.util.Date;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "Travel")
public class Travel {

	private int id;
	private Date startDate;
	private Date endDate;
	private String description;

	@DynamoDBHashKey(attributeName = "Id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@DynamoDBAttribute(attributeName = "StartDate")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@DynamoDBAttribute(attributeName = "EndDate")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@DynamoDBAttribute(attributeName = "Description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	final static class TravelBuilder {
		private int id;
		private Date startDate;
		private Date endDate;
		private String description;

		public TravelBuilder withId(int id) {
			this.id = id;
			return this;
		}

		public TravelBuilder withStartDate(Date startDate) {
			this.startDate = startDate;
			return this;
		}

		public TravelBuilder withEndDate(Date endDate) {
			this.endDate = endDate;
			return this;
		}

		public TravelBuilder withDescription(String description) {
			this.description = description;
			return this;
		}

		public Travel build() {
			Travel travel = new Travel();
			travel.id = id;
			travel.startDate = startDate;
			travel.endDate = endDate;
			travel.description = description;
			return travel;
		}
	}

}
