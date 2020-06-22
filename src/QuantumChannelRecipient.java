public interface QuantumChannelRecipient
{
    PolarizationQubit receiveQubit(PolarizationQubit qb);

    PolarizationQubit sendQubit(PolarizationQubit qb);
}
