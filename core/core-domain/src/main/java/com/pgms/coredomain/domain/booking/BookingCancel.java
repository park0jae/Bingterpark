package com.pgms.coredomain.domain.booking;

import com.pgms.coredomain.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "booking_cancel")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class BookingCancel extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "reason", nullable = false)
    private String reason;

    @Column(name = "amount", nullable = false)
    private int amount;

    @Column(name = "created_by", nullable = false)
    private String createdBy;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Booking booking;

    @Enumerated(EnumType.STRING)
    @Column(name = "refund_ bank_code")
    private BankCode refundBankCode;

    @Column(name = "refund_ account_number")
    private String refundAccountNumber;

    @Column(name = "refund_ holder_name")
    private String refundHolderName;

    @Builder
    public BookingCancel(String reason, int amount, String createdBy, Booking booking) {
        this.reason = reason;
        this.amount = amount;
        this.createdBy = createdBy;
        this.booking = booking;
    }

    public void updateBooking(Booking booking) {
        this.booking = booking;
    }

    public void updateRefundInfo(String refundBankCode, String refundAccountNumber, String refundHolderName) {
        this.refundBankCode = BankCode.getByBankNumCode(refundBankCode);
        this.refundAccountNumber = refundAccountNumber;
        this.refundHolderName = refundHolderName;
    }
}
