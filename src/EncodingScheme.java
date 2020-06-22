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

    static int decodeQubit(PolarizationQubit qb){
        switch(qb.getPolarization()){
	    case H:
	        return 0;
	    case V:
	        return 1;
	    case P:
	        return 0;
	    case M:
	        return 1;
	    default:
	        return -1;
	}

    }


}
