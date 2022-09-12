import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class User {
    private String phoneNo;
    private String name;
    private int totalAmoutNeedsToGive;
    private int toatlAmountNeedsToGet;
}
