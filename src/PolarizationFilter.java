import java.util.Random;

public class PolarizationFilter
{
    FilterSetting s;
    Random r = new Random();

    public PolarizationFilter(final FilterSetting s) {
	this.s = s;
    }

    public void setFilterSetting(final FilterSetting s) {
	this.s = s;
    }

    PolarizationQubit randomizePolarization(PolarizationQubit qb){
        if(s == FilterSetting.HV){
            if(r.nextBoolean()){
                qb.setPolarization(Polarization.V);
	    }else
	        qb.setPolarization(Polarization.H);
	}else if(s == FilterSetting.PM){
	    if(r.nextBoolean()){
		qb.setPolarization(Polarization.P);
	    }else
		qb.setPolarization(Polarization.M);
	}
        return qb;
    }

    PolarizationQubit filterQubit(PolarizationQubit qb) {
	System.out.println("Measuring qubit " + qb.getPolarization() + " with setting " + this.s);
	if (s == FilterSetting.HV && (qb.getPolarization() == Polarization.H || qb.getPolarization() == Polarization.V)) {
	    // Correct basis, return qubit as-is
	    return qb;
	} else if (s == FilterSetting.HV && (qb.getPolarization() == Polarization.P || qb.getPolarization() == Polarization.M)) {
	    // Wrong basis -- randomize state
	    return randomizePolarization(qb);
	} else if (s == FilterSetting.PM &&
		   (qb.getPolarization() == Polarization.P || qb.getPolarization() == Polarization.M)) {
	    return qb;
	} else if (s == FilterSetting.PM && (qb.getPolarization() == Polarization.H || qb.getPolarization() == Polarization.V)) {
	    // Wrong basis -- randomize state
	    return randomizePolarization(qb);
	} else {
	    // Error state, should not get here
	    qb.setPolarization(Polarization.UNSET);
	    return qb;
	}
    }

}
