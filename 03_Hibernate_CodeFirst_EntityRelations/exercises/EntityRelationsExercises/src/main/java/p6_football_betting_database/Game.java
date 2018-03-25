package p6_football_betting_database;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;


@Entity
@Table(name = "games")
public class Game implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(targetEntity = Team.class)
    private Team home;

    @OneToOne(targetEntity = Team.class)
    private Team away;

    @Basic
    private int homeGoals;

    @Basic
    private int awayGoals;

    @Basic
    private Date timeOfGame;

    @Basic
    private BigDecimal homeWinRate;

    @Basic
    private BigDecimal awayWinRate;

    @Basic
    private BigDecimal drawWinRate;

    @ManyToOne(targetEntity = Round.class)
    private Round round;

    @ManyToOne(targetEntity = Competition.class)
    private Competition competition;

    @ManyToMany(mappedBy = "games")
    private Set<Player> players;


    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Team getHome() {
        return home;
    }

    public void setHome(Team home) {
        this.home = home;
    }

    public Team getAway() {
        return away;
    }

    public void setAway(Team away) {
        this.away = away;
    }

    public int getHomeGoals() {
        return homeGoals;
    }

    public void setHomeGoals(int homeGoals) {
        this.homeGoals = homeGoals;
    }

    public int getAwayGoals() {
        return awayGoals;
    }

    public void setAwayGoals(int awayGoals) {
        this.awayGoals = awayGoals;
    }

    public Date getTimeOfGame() {
        return timeOfGame;
    }

    public void setTimeOfGame(Date timeOfGame) {
        this.timeOfGame = timeOfGame;
    }

    public BigDecimal getHomeWinRate() {
        return homeWinRate;
    }

    public void setHomeWinRate(BigDecimal homeWinRate) {
        this.homeWinRate = homeWinRate;
    }

    public BigDecimal getAwayWinRate() {
        return awayWinRate;
    }

    public void setAwayWinRate(BigDecimal awayWinRate) {
        this.awayWinRate = awayWinRate;
    }

    public BigDecimal getDrawWinRate() {
        return drawWinRate;
    }

    public void setDrawWinRate(BigDecimal drawWinRate) {
        this.drawWinRate = drawWinRate;
    }

    public Round getRound() {
        return round;
    }

    public void setRound(Round round) {
        this.round = round;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }
}
