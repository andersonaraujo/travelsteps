package org.andersonaraujo.travelsteps.travels.db;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.andersonaraujo.travelsteps.travels.db.Travel.TravelBuilder;
import org.junit.Test;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;

public class TravelTest {

	private static final SimpleDateFormat SDF = new SimpleDateFormat(
			"dd/MM/yyyy");

	// Test values
	private static final int ID = 1;
	private static final String DESCRIPTION = "I'm going to Caribean!!";
	private static Date START_DATE;
	private static Date END_DATE;
	static {
		try {
			START_DATE = SDF.parse("01/01/2015");
			END_DATE = SDF.parse("31/01/2015");
		} catch (ParseException e) {
			// Ignore
		}
	}

	// Setup DB
	private static final DynamoDBMapper MAPPER;
	static {
		AmazonDynamoDBClient client = new AmazonDynamoDBClient(
				new ProfileCredentialsProvider(
						"/Users/andersonaraujo/.aws/andersonaraujo_credentials",
						"andersonaraujo"));
		MAPPER = new DynamoDBMapper(client);
	}

	@Test
	public void nominalTestInsertAndReadsOneElement() {

		// Save travel
		Travel travelToSave = createTravel();
		MAPPER.save(travelToSave);

		// Reads from DB and verify it has the right values
		DynamoDBQueryExpression<Travel> queryExpression = new DynamoDBQueryExpression<Travel>()
				.withHashKeyValues(new TravelBuilder().withId(ID).build());

		PaginatedQueryList<Travel> travelReadList = MAPPER.query(Travel.class,
				queryExpression);

		verify(travelToSave, travelReadList.get(0));

	}

	/**
	 * Creates a {@link Travel} instance with test values;
	 * 
	 * @return Travel instance
	 */
	private Travel createTravel() {

		return new TravelBuilder().withId(ID).withStartDate(START_DATE)
				.withEndDate(END_DATE).withDescription(DESCRIPTION).build();

	}

	/**
	 * Verifies that the Travel instance read from DB has the correct values.
	 * 
	 * @param expectedTravel
	 *            Travel instance with expected values.
	 * @param readTravel
	 *            Travel instance read from DB.
	 */
	private void verify(Travel expectedTravel, Travel readTravel) {
		assertEquals(expectedTravel.getId(), readTravel.getId());
		assertEquals(expectedTravel.getStartDate(), readTravel.getStartDate());
		assertEquals(expectedTravel.getEndDate(), readTravel.getEndDate());
		assertEquals(expectedTravel.getDescription(),
				readTravel.getDescription());
	}

}
