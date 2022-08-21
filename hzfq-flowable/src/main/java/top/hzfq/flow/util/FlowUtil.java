package top.hzfq.flow.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.BinaryNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedGenerator;
import org.flowable.bpmn.converter.BpmnXMLConverter;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.common.engine.impl.util.io.BytesStreamSource;
import org.flowable.editor.language.json.converter.BpmnJsonConverter;
import org.flowable.engine.repository.Model;
import org.flowable.image.ProcessDiagramGenerator;
import org.flowable.image.impl.DefaultProcessDiagramGenerator;
import org.flowable.validation.ProcessValidator;
import org.flowable.validation.ProcessValidatorFactory;
import org.flowable.validation.ValidationError;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author huzhifengqing@qq.com
 * @since 2022/8/21
 */
public class FlowUtil {

    public static final BpmnJsonConverter BPMN_JSON_CONVERTER = new BpmnJsonConverter();

    public static final BpmnXMLConverter BPMN_XML_CONVERTER = new BpmnXMLConverter();

    public static final ProcessDiagramGenerator PROCESS_DIAGRAM_GENERATOR = new DefaultProcessDiagramGenerator();

    public static final XMLInputFactory XML_INPUT_FACTORY = xmlInputFactory();

    public static final ProcessValidator PROCESS_VALIDATOR =
            new ProcessValidatorFactory().createDefaultProcessValidator();

    public static final TimeBasedGenerator TIME_BASED_GENERATOR =
            Generators.timeBasedGenerator(EthernetAddress.fromInterface());

    public static String generateId() {
        return TIME_BASED_GENERATOR.generate().toString();
    }

    public static BpmnModel extractBpmnModel(InputStream modelInputStream) throws XMLStreamException {
        XMLInputFactory xif = XML_INPUT_FACTORY;
        InputStreamReader xmlIn = new InputStreamReader(modelInputStream, StandardCharsets.UTF_8);
        XMLStreamReader xtr = xif.createXMLStreamReader(xmlIn);
        return BPMN_XML_CONVERTER.convertToBpmnModel(xtr);
    }

    public static List<ValidationError> validateBpmnModel(BpmnModel bpmnModel) {
        return PROCESS_VALIDATOR.validate(bpmnModel);
    }

    public static byte[] convertToXml(BpmnModel bpmnModel) {
        return BPMN_XML_CONVERTER.convertToXML(bpmnModel, StandardCharsets.UTF_8.name());
    }

    public static BpmnModel convertXmlToBpmnModel(byte[] xml) {
        return BPMN_XML_CONVERTER.convertToBpmnModel(new BytesStreamSource(xml),
                true, true, StandardCharsets.UTF_8.name());
    }

    public static InputStream generateJpgDiagram(BpmnModel bpmnModel) {
        return PROCESS_DIAGRAM_GENERATOR.generateJpgDiagram(bpmnModel);
    }

    private static XMLInputFactory xmlInputFactory() {
        XMLInputFactory xif = XMLInputFactory.newInstance();
        if (xif.isPropertySupported(XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES)) {
            xif.setProperty(XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES, false);
        }

        if (xif.isPropertySupported(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES)) {
            xif.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, false);
        }

        if (xif.isPropertySupported(XMLInputFactory.SUPPORT_DTD)) {
            xif.setProperty(XMLInputFactory.SUPPORT_DTD, false);
        }
        return xif;
    }
}
