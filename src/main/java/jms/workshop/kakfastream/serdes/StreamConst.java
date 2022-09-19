package jms.workshop.kakfastream.serdes;

import jms.workshop.kakfastream.model.Employee;
import jms.workshop.kakfastream.serdes.convertors.EmployeeDeserializer;
import jms.workshop.kakfastream.serdes.convertors.EmployeeSerializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;

public interface StreamConst {
    Serde<String> STRING_SERDE = Serdes.String();

    static Serde<Employee> EMPLOYEE_SERDE() {
        EmployeeSerializer serializer = new EmployeeSerializer();
        EmployeeDeserializer deserializer = new EmployeeDeserializer();
        return Serdes.serdeFrom(serializer, deserializer);
    }
}
