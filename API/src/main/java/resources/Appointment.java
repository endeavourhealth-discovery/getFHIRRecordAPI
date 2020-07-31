package resources;

import org.endeavourhealth.getFHIRRecordAPI.common.constants.ResourceConstants;
import org.endeavourhealth.getFHIRRecordAPI.common.models.AppointmentFull;
import org.hl7.fhir.dstu3.model.Coding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class Appointment {

	/**
	 *
	 * @param appointmentResult
	 * @return
	 * @throws Exception
	 */
	public static org.hl7.fhir.dstu3.model.Appointment getAppointmentResource(AppointmentFull appointmentResult) throws Exception {
		int actualDuration = appointmentResult.getActualDuration();
		String startdate = replaceNull(appointmentResult.getStartDate());
		int plannedDuration = appointmentResult.getPlannedDuration();
		String type = replaceNull(appointmentResult.getType());
		UUID id = UUID.randomUUID();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date startDt = formatter.parse(startdate);

		org.hl7.fhir.dstu3.model.Appointment appointment = new org.hl7.fhir.dstu3.model.Appointment();
		appointment.addIdentifier()
				.setValue(String.valueOf(appointmentResult.getId()))
				.setSystem(ResourceConstants.SYSTEM_ID);
		appointment.setId(String.valueOf(id));

		appointment.getMeta().addProfile("https://fhir.hl7.org.uk/STU3/StructureDefinition/CareConnect-Appointment-1");
		appointment.setMinutesDuration(actualDuration);
		appointment.setCreated(startDt);
		if(appointmentResult.getStatus()!=null)
		appointment.setStatus(new org.hl7.fhir.dstu3.model.Appointment.AppointmentStatusEnumFactory().fromCode(appointmentResult.getStatus().toLowerCase ()));

		Coding coding1 = new Coding();
		/*coding1.setSystem("");*/
		coding1.setCode("gp");
		coding1.setDisplay("General Practice");
		appointment.getServiceCategory().addCoding(coding1);

		Coding coding2 = new Coding();
		/*coding2.setSystem("");*/
		coding2.setCode("gp");
		coding2.setDisplay("General Practice");
		appointment.addSpecialty().addCoding(coding2);

		Coding coding = new Coding();
		coding.setDisplay(type);
		appointment.getAppointmentType().addCoding(coding);
		
		return appointment;
	}

	/**
	 *
	 * @param appointmentResult
	 * @return
	 * @throws Exception
	 */
	public org.hl7.fhir.dstu3.model.Slot getSlotResource(AppointmentFull appointmentResult) throws Exception {
		String startdate = replaceNull(appointmentResult.getStartDate());
		int plannedDuration = appointmentResult.getPlannedDuration();
		int actualDuration = appointmentResult.getActualDuration();
		UUID id = UUID.randomUUID();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date startDt = formatter.parse(startdate);

		org.hl7.fhir.dstu3.model.Slot slot = new org.hl7.fhir.dstu3.model.Slot();

		slot.setId(String.valueOf(id));
		slot.getMeta().addProfile("https://fhir.hl7.org.uk/STU3/StructureDefinition/CareConnect-Slot-1");
		slot.setStart(startDt);

		if(actualDuration != 0 || plannedDuration != 0) {
			Calendar c = Calendar.getInstance();
			c.setTime(startDt);
            c.add(Calendar.MINUTE,actualDuration != 0 ? actualDuration  : plannedDuration);
			slot.setEnd(c.getTime());
		}

		return slot;
	}

	/**
	 *
	 * @param appointmentResult
	 * @return
	 * @throws Exception
	 */
	public org.hl7.fhir.dstu3.model.Schedule getScheduleResource(AppointmentFull appointmentResult) throws Exception {
		String startdate = replaceNull(appointmentResult.getStartDate());
		int plannedDuration = appointmentResult.getPlannedDuration();
		UUID id = UUID.randomUUID();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date startDt = formatter.parse(startdate);

		org.hl7.fhir.dstu3.model.Schedule schedule = new org.hl7.fhir.dstu3.model.Schedule();
		schedule.setId(String.valueOf(id));
		schedule.getMeta().addProfile("https://fhir.hl7.org.uk/STU3/StructureDefinition/CareConnect-Schedule-1");

		return schedule;
	}

	/**
	 *
	 * @param input
	 * @return
	 */
	public static String replaceNull(String input) {
		return input == null ? "" : input;
	}

}
