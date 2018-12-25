package lambda.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Artist {
    private String name;
    private List<String> members;
    private String origin;

    public boolean isFrom(String origin) {
        return this.origin.equals(origin);
    }
}
