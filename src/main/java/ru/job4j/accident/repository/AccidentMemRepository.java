package ru.job4j.accident.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

@Repository
public class AccidentMemRepository {

    private static final AtomicInteger ACCIDENT_ID = new AtomicInteger(4);

    private final HashMap<Integer, Accident> accidents = new HashMap<>();

    private final HashMap<Integer, AccidentType> accidentTypes = new HashMap<>();

    private final HashMap<Integer, Rule> accidentRules = new HashMap<>();

    public AccidentMemRepository() {
        accidents.put(1, Accident.builder()
                                 .id(1)
                                 .name("ДТП")
                                 .text("Столкновение на кольце")
                                 .address("г.Москва, Садовое кольцо")
                                 .accidentType(AccidentType.builder()
                                                           .id(1)
                                                           .name("Две машины")
                                                           .build())
                                 .rules(List.of(Rule.builder()
                                                   .id(2)
                                                   .name("Статья. 2")
                                                   .build()))
                                 .build());
        accidents.put(2, Accident.builder()
                                 .id(2)
                                 .name("ДТП")
                                 .text("Столкновение на перекрестке")
                                 .address("г.Липецк, ул. Меркулова")
                                 .accidentType(AccidentType.builder()
                                                           .id(2)
                                                           .name("Машина и человек")
                                                           .build())
                                 .rules(List.of(
                                         Rule.builder()
                                             .id(1)
                                             .name("Статья. 1")
                                             .build(),
                                         Rule.builder()
                                             .id(3)
                                             .name("Статья. 3")
                                             .build()))
                                 .build());
        accidents.put(3, Accident.builder()
                                 .id(3)
                                 .name("ДТП")
                                 .text("Авария на пешеходном переходе")
                                 .address("г.Воронеж, Московский проспект")
                                 .accidentType(AccidentType.builder()
                                                           .id(3)
                                                           .name("Машина и велосипед")
                                                           .build())
                                 .rules(List.of(
                                         Rule.builder()
                                             .id(1)
                                             .name("Статья. 1")
                                             .build(),
                                         Rule.builder()
                                             .id(2)
                                             .name("Статья. 2")
                                             .build(),
                                         Rule.builder()
                                             .id(3)
                                             .name("Статья. 3")
                                             .build()))
                                 .build());
        accidentTypes.put(1, AccidentType.builder()
                                         .id(1)
                                         .name("Две машины")
                                         .build());
        accidentTypes.put(2, AccidentType.builder()
                                         .id(2)
                                         .name("Машина и человек")
                                         .build());
        accidentTypes.put(3, AccidentType.builder()
                                         .id(3)
                                         .name("Машина и велосипед")
                                         .build());
        accidentRules.put(1, Rule.builder()
                                 .id(1)
                                 .name("Статья. 1")
                                 .build());
        accidentRules.put(2, Rule.builder()
                                 .id(2)
                                 .name("Статья. 2")
                                 .build());
        accidentRules.put(3, Rule.builder()
                                 .id(3)
                                 .name("Статья. 3")
                                 .build());
    }

    public void add(Accident accident) {
        var id = checkAndGetAccidentId(accident);
        if (!accidents.containsKey(id)) {
            accidents.put(id, accident);
        }
    }

    public void update(Integer id, Accident accident) {
        accidents.replace(id, accident);
    }

    public List<Accident> findAllAccidents() {
        return new ArrayList<>(accidents.values());
    }

    public Accident getById(Integer id) {
        return accidents.get(id);
    }

    public List<AccidentType> getAllAccidentTypes() {
        return new ArrayList<>(accidentTypes.values());
    }

    public AccidentType getAccidentTypeById(Integer id) {
        return accidentTypes.get(id);
    }

    public List<Rule> getAllAccidentRules() {
        return new ArrayList<>(accidentRules.values());
    }

    public Rule getAccidentRule(Integer id) {
        return accidentRules.get(id);
    }

    private Integer checkAndGetAccidentId(Accident accident) {
        var id = accident.getId();
        if (id == 0) {
            id = ACCIDENT_ID.incrementAndGet();
        }
        return id;
    }

}
