package ru.job4j.accident.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

@Repository
@RequiredArgsConstructor
public class AccidentJdbcTemplate {

    private static final String FIND_ALL_ACCIDENTS_QUERY =
            "select a.id, a.name, a.text, a.address, "
                    + "at.id as type_id, at.name as type_name, "
                    + "r.id as rule_id, r.name as rule_name "
                    + "from accident a "
                    + "join types at on at.id = a.accident_type_id "
                    + "join accident_rule ar on a.id = ar.accident_id "
                    + "join rules r on r.id  = ar.rule_id";

    private final JdbcTemplate jdbcTemplate;

    public Accident save(Accident accident, String[] ruleIds) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps =
                    connection.prepareStatement(
                            "insert into accident (name, text, address, accident_type_id) "
                                    + "values (?, ?, ?, ?)",
                            new String[] {"id"});
            ps.setString(1, accident.getName());
            ps.setString(2, accident.getText());
            ps.setString(3, accident.getAddress());
            ps.setInt(4, accident.getAccidentType().getId());
            return ps;
        }, keyHolder);
        Integer accidentId = (Integer) keyHolder.getKey();
        for (String id : ruleIds) {
            jdbcTemplate.update(
                    "insert into accident_rule(accident_id, rule_id) values (?, ?)",
                    accidentId,
                    Integer.parseInt(id));
        }
        return accident;
    }

    public void update(Accident accident) {
        jdbcTemplate.update(
                "update accident "
                        + "set name = ?, "
                        + "text = ?, "
                        + "address = ?, "
                        + "accident_type_id = ? "
                        + "where id = ?",
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getAccidentType().getId(),
                accident.getId()
        );
    }

    public List<Accident> findAllAccidents() {
        return Optional.ofNullable(
                jdbcTemplate.query(FIND_ALL_ACCIDENTS_QUERY, accidentResultSetExtractor())
                            .values().stream()
                                      .collect(Collectors.toList())
                ).orElse(Collections.emptyList());
    }

    public Accident getAccidentById(Integer id) {
        return jdbcTemplate.queryForObject(
                "select id, name, text, address from accident where id = ?",
                (rs, rowNum) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    accident.setText(rs.getString("text"));
                    accident.setAddress(rs.getString("address"));
                    return accident;
                },
                id);
    }

    public List<AccidentType> getAllAccidentTypes() {
        return jdbcTemplate.query(
                "select id, name from types",
                (rs, rowNum) -> {
                    AccidentType accidentType = new AccidentType();
                    accidentType.setId(rs.getInt("id"));
                    accidentType.setName(rs.getString("name"));
                    return accidentType;
                });
    }

    public AccidentType getAccidentTypeById(Integer id) {
        return jdbcTemplate.queryForObject(
                "select id, name from types where id = ?",
                (rs, rowNum) -> {
                    AccidentType accidentType = new AccidentType();
                    accidentType.setId(rs.getInt("id"));
                    accidentType.setName(rs.getString("name"));
                    return accidentType;
                },
                id);
    }

    public List<Rule> getAllAccidentRules() {
        return jdbcTemplate.query(
                "select id, name from rules",
                (rs, rowNum) -> {
                    Rule accidentRule = new Rule();
                    accidentRule.setId(rs.getInt("id"));
                    accidentRule.setName(rs.getString("name"));
                    return accidentRule;
                });
    }

    public Rule getAccidentRuleById(Integer id) {
        return jdbcTemplate.queryForObject(
                "select id, name from rules where id = ?",
                (rs, rowNum) -> {
                    Rule accidentRule = new Rule();
                    accidentRule.setId(rs.getInt("id"));
                    accidentRule.setName(rs.getString("name"));
                    return accidentRule;
                },
                id);
    }

    private ResultSetExtractor<Map<Integer, Accident>> accidentResultSetExtractor() {
        return new ResultSetExtractor<Map<Integer, Accident>>() {
            @Override
            public Map<Integer, Accident> extractData(ResultSet resultSet)
                    throws SQLException, DataAccessException {
                Map<Integer, Accident> result = new HashMap<>();
                while (resultSet.next()) {
                    Accident accident =
                            Accident.builder()
                                    .id(resultSet.getInt("id"))
                                    .name(resultSet.getString("name"))
                                    .text(resultSet.getString("text"))
                                    .address(resultSet.getString("address"))
                                    .accidentType(
                                            AccidentType.builder()
                                                        .id(resultSet.getInt("type_id"))
                                                        .name(resultSet.getString("type_name"))
                                                        .build()
                                    )
                                    .rules(new HashSet<>())
                                    .build();
                    result.putIfAbsent(accident.getId(), accident);
                    result.get(accident.getId())
                          .addRule(Rule.builder()
                                       .id(resultSet.getInt("rule_id"))
                                       .name(resultSet.getString("rule_name"))
                                       .build());
                }
                return result;
            }
        };
    }

}
