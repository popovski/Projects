package mk.com.codefactory.advanced;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import mk.com.codefactory.basic.User;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Driver extends User {
    private String driverLicenceNumber;
    @OneToMany(mappedBy = "driver")
    private List<Ride> rides = new ArrayList<>();
}
