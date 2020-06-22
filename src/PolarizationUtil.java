import java.util.Random;
import java.util.logging.Filter;

public class PolarizationUtil
{
    static Random r = new Random();
    static Polarization getRandomPolarization(){
        return Polarization.values()[r.nextInt(Polarization.values().length)];
    }

    static FilterSetting getRandomBasis(){
	return FilterSetting.values()[r.nextInt(FilterSetting.values().length)];
    }

    static FilterSetting[] getRandomBasisSelection(int n){
        FilterSetting[] basis = new FilterSetting[n];

        for (int i = 0; i < n; i++) {
            basis[i] = getRandomBasis();
        }
        return basis;
    }

}
