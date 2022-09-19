package jms.workshop.kakfastream.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private Long id;
    private String name;
    private String company;
    private String position;
    private Long experience;
}
