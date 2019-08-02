package com.crmpetproject.crmpetproject.servives.impl;

import com.crmpetproject.crmpetproject.models.Client;
import com.crmpetproject.crmpetproject.models.ActiveClient;
import com.crmpetproject.crmpetproject.models.ActiveClientStatus;
import com.crmpetproject.crmpetproject.repository.interfaces.ActiveClientRepository;
import com.crmpetproject.crmpetproject.repository.interfaces.ActiveClientRepositoryCustom;
import com.crmpetproject.crmpetproject.repository.interfaces.ActiveClientStatusRepository;
import com.crmpetproject.crmpetproject.servives.interfaces.ActiveClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ActiveClientServiceImpl extends CommonServiceImpl<ActiveClient> implements ActiveClientService {

    private final ActiveClientRepository studentRepository;
    private final ActiveClientStatusRepository studentStatusRepository;
    private final ActiveClientRepositoryCustom studentRepositoryCustom;

    @Autowired
    public ActiveClientServiceImpl(ActiveClientRepository studentRepository,
                                   ActiveClientStatusRepository studentStatusRepository,
                                   ActiveClientRepositoryCustom studentRepositoryCustom) {
        this.studentRepository = studentRepository;
        this.studentStatusRepository = studentStatusRepository;
        this.studentRepositoryCustom = studentRepositoryCustom;
    }

    @Value("${price.month}")
    private String PRICE;

    @Value("${default.student.status}")
    private String DEFAULT_STATUS;

    @Override
    public ActiveClient addActiveClientForClient(Client client) {
        ActiveClient result;
        if (client.getActiveClient() == null && client.getStatus().isCreateStudent()) {
            ActiveClientStatus status = studentStatusRepository.getStudentStatusByStatus(DEFAULT_STATUS);
            if (status == null) {
                status = studentStatusRepository.save(new ActiveClientStatus(DEFAULT_STATUS));
            }
            int trialOffset = client.getStatus().getTrialOffset();
            int nextPaymentOffset = client.getStatus().getNextPaymentOffset();
            result = new ActiveClient(client,
                    LocalDateTime.now().plusDays(trialOffset),
                    LocalDateTime.now().plusDays(nextPaymentOffset),
                    new BigDecimal(0.00),
                    new BigDecimal(0.00),
                    new BigDecimal(0.00),
                    status,
                    "");
            result = studentRepository.save(result);
        } else {
            result = client.getActiveClient();
        }
        return result;
    }

    @Override
    public List<ActiveClient> getActiveClientByStatusId(Long id) {
        return studentRepository.getActiveClientByStatusId(id);
    }

    @Override
    public List<ActiveClient> getStudentsWithTodayNotificationsEnabled() {
        return studentRepositoryCustom.getStudentsWithTodayNotificationsEnabled();
    }

    @Override
    public void detach(ActiveClient student) {
        studentRepositoryCustom.detach(student);
    }
}
