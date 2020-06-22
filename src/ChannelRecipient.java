public interface ChannelRecipient
{
    PolarizationQubit receiveQubit(PolarizationQubit qb);

    PolarizationQubit sendQubit(PolarizationQubit qb);
}
