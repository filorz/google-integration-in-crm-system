package com.crmpetproject.crmpetproject.models;

import org.apache.commons.lang3.builder.DiffBuilder;
import org.apache.commons.lang3.builder.DiffResult;
import org.apache.commons.lang3.builder.Diffable;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(name = "active_client")
@Entity
public class ActiveClient implements Diffable<ActiveClient> {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @JoinColumn(name = "client_id")
    @OneToOne
    private Client client;

    @Column(name = "end_trial")
    private LocalDateTime trialEndDate;

    @Column(name = "next_pay")
    private LocalDateTime nextPaymentDate;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "amount")
    private BigDecimal paymentAmount;

    @Column(name = "later")
    private BigDecimal payLater;

    @JoinColumn(name = "status_id")
    @OneToOne
    private ActiveClientStatus status;

    @Column(name = "notes")
    private String notes;

    public ActiveClient() {
    }

    public ActiveClient(Client client, LocalDateTime trialEndDate, LocalDateTime nextPaymentDate, BigDecimal price, BigDecimal paymentAmount, BigDecimal payLater, ActiveClientStatus status, String notes) {
        this.client = client;
        this.trialEndDate = trialEndDate;
        this.nextPaymentDate = nextPaymentDate;
        this.price = price;
        this.paymentAmount = paymentAmount;
        this.payLater = payLater;
        this.status = status;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LocalDateTime getTrialEndDate() {
        return trialEndDate;
    }

    public void setTrialEndDate(LocalDateTime trialEndDate) {
        this.trialEndDate = trialEndDate;
    }

    public LocalDateTime getNextPaymentDate() {
        return nextPaymentDate;
    }

    public void setNextPaymentDate(LocalDateTime nextPaymentDate) {
        this.nextPaymentDate = nextPaymentDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public BigDecimal getPayLater() {
        return payLater;
    }

    public void setPayLater(BigDecimal payLater) {
        this.payLater = payLater;
    }

    public ActiveClientStatus getStatus() {
        return status;
    }

    public void setStatus(ActiveClientStatus status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", client=" + client +
                ", trialEndDate=" + trialEndDate +
                ", nextPaymentDate=" + nextPaymentDate +
                ", price=" + price +
                ", paymentAmount=" + paymentAmount +
                ", payLater=" + payLater +
                ", status=" + status +
                ", notes='" + notes +
                '}';
    }

    @Override
    public DiffResult diff(ActiveClient student) {
        return new DiffBuilder(this, student, ToStringStyle.JSON_STYLE)
                .append("Клиент", this.client.getId(), student.client.getId())
                .append("Дата пробных", this.trialEndDate.toLocalDate(), student.trialEndDate.toLocalDate())
                .append("Дата оплаты", this.nextPaymentDate.toLocalDate(), student.nextPaymentDate.toLocalDate())
                .append("Цена", this.price.toString().contains(".00") ? this.price.toBigInteger().toString() : this.price.toString(), student.price.toString().contains(".00") ? student.price.toBigInteger().toString() : student.price.toString())
                .append("Платёж", this.paymentAmount.toString().contains(".00") ? this.paymentAmount.toBigInteger().toString() : this.paymentAmount.toString(), student.paymentAmount.toString().contains(".00") ? student.paymentAmount.toBigInteger().toString() : student.paymentAmount.toString())
                .append("Оплата позже", this.payLater.toString().contains(".00") ? this.payLater.toBigInteger().toString() : this.payLater.toString(), student.payLater.toString().contains(".00") ? student.payLater.toBigInteger().toString() : student.payLater.toString())
                .append("Статус обучения", this.status.getStatus(), student.status.getStatus())
                .append("Заметки", this.notes, student.notes)
                .build();
    }
}
