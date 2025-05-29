package com.quantumsoft.insurance.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.quantumsoft.insurance.entity.Teleconsultation;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class PrescriptionPdfGenerator {

    public static ByteArrayInputStream generatePrescriptionPdf(Teleconsultation tele) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Font bodyFont = FontFactory.getFont(FontFactory.HELVETICA, 12);

            document.add(new Paragraph("Prescription", titleFont));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Patient: " + tele.getAppointment().getUser().getFullName(), bodyFont));
            document.add(new Paragraph("Doctor: " + tele.getAppointment().getDoctor().getName(), bodyFont));
            document.add(new Paragraph("Date: " + tele.getAppointment().getAppointmentDateTime(), bodyFont));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Diagnosis: " + tele.getDiagnosis(), bodyFont));
            document.add(new Paragraph("Prescription: " + tele.getPrescription(), bodyFont));
            document.add(new Paragraph("Notes: " + tele.getNotes(), bodyFont));

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
