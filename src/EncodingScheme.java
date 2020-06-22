public class EncodingScheme
{
    static PolarizationQubit encodeQubit(int dataBit, FilterSetting basisSelection){
        PolarizationQubit qb = new PolarizationQubit();
	qb.setPolarization(Polarization.UNSET);

        // HV basis. H=0, V=1
        if (basisSelection == FilterSetting.HV){
            if(dataBit == 0){
                qb.setPolarization(Polarization.H);
	    }else{
                qb.setPolarization(Polarization.V);
	    }
	}else if(basisSelection == FilterSetting.PM){
	    // PM basis. P=0, M=1

	    if(dataBit == 0){
		qb.setPolarization(Polarization.P);
	    }else{
		qb.setPolarization(Polarization.M);
	    }
	}
	return qb;
    }
}
