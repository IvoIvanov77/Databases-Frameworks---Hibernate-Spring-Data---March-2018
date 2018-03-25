package p6_football_betting_database;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "positions")
public class Position {

    @Id
    @Column(length = 3, unique = true, nullable = false)
    private String positionInitials;

    @Basic
    private String description;

    @OneToMany(mappedBy = "position")
    private Set<Player> players;

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

    public String getPositionInitials() {
        return positionInitials;
    }

    public void setPositionInitials(String positionInitials) {
        this.positionInitials = positionInitials;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
