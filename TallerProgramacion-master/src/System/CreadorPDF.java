package System;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map.Entry;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.io.image.ImageType;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.*;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.DashedBorder;
import com.itextpdf.layout.borders.DoubleBorder;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.BlockElement;
import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.ListItem;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.ListNumberingType;

import Modelos.Pieza;
import Modelos.Factura;
import Modelos.Reparacion;




public class CreadorPDF {
	
	
	private static final Border bordeDoble = new DoubleBorder(1.0f);
	private static final Border bordeSolido = new SolidBorder(1.0f);
	private static final Border borde_seccion = new DashedBorder(1.0f);
	
    public static void crearPdfFactura(Factura factura) throws FileNotFoundException {
    	String dest = "facturas/Factura "+factura.getId()+".pdf";
    	File file = new File(dest);
		file.getParentFile().mkdirs();

    	//Inicializar escritor pdf
    	FileOutputStream fos = new FileOutputStream(dest);
    	PdfWriter writer = new PdfWriter(fos);
    	
    	//Inicializar documento pdf
    	PdfDocument pdf = new PdfDocument(writer);
    	
    	//Inicializar documento
    	Document documento = new Document(pdf,PageSize.A4);
    	//Escribir en el documento
		Paragraph cabezero = new Paragraph();

		try {
			String url = "/Resources/imagenes/llave-inglesa.png";
			ImageData data = ImageDataFactory.create(CreadorPDF.class.getResource("/Resources/imagenes/llave-inglesa.png"));
			Image img = new Image(data);
			cabezero.add(img);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		cabezero.add(new Text("Hermanos Gutierrez S.A").setFontSize(20f));
    	documento.add(cabezero);
    	documento.add(new Paragraph("Cliente : " +factura.getCoche().getDueno().toString()).setBorder(borde_seccion));
    	documento.add(new Paragraph("Vehiculo: "+ factura.getCoche().toString()).setBorder(borde_seccion));
    	documento.add(new Paragraph("Fecha entrada: "+ factura.getFecha_entrada()).setBorder(borde_seccion));
    	documento.add(new Paragraph("Fecha Fin: "+ factura.getFecha_fin()).setBorder(borde_seccion));
    	documento.add(new Paragraph("Operaciones:"));
    	List lista = new List(ListNumberingType.DECIMAL).setBorder(bordeSolido);
    	for (Reparacion rep : factura.getReparaciones()) {
			ListItem reparacion = new ListItem();
			
    		reparacion.add(new Paragraph("Descripcion de operacion: "+ rep.getComentarios()));
    		Table piezas = new Table(4);
    		piezas.addHeaderCell("Marca");
        	piezas.addHeaderCell("Modelo");
        	piezas.addHeaderCell("Precio Ud");
        	piezas.addHeaderCell("Cantidad");
        	try {
        		for (Entry<Pieza,Integer> linea : rep.getPiezas().entrySet()) {
        			piezas.startNewRow();
        			piezas.addCell(linea.getKey().getMarca());
        			piezas.addCell(linea.getKey().getModelo());
        			piezas.addCell(Double.toString(linea.getKey().getPrecio()));
        			piezas.addCell(Integer.toString(linea.getValue()));
        		}
        		reparacion.add(piezas);
        	} catch(Exception e) {
        		//En caso de una operacion sin piezas
        	}
			reparacion.add(new Paragraph("Mano de obra: "+ 10*rep.getDuracion()+"\u20ac"));
			lista.add(reparacion);

    	} 
		documento.add(lista);
    	
    	documento.add(new Paragraph("Total piezas y mano de obra: "+factura.getPrecio_total()+"\u20ac").setBorder(bordeDoble));
    	
    	documento.close();
    	try {
			java.awt.Desktop.getDesktop().edit(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}