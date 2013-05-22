package atl.space.world.maps;

import java.util.Iterator;
import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import atl.space.entities.Entity;

/*
 * deprecated. Unlikely to be used
 */
public class NumericalAverageMap extends AbstractMap<Integer> {
	public NumericalAverageMap(List<Entity> entities) {
		//Not done
	}
	@Override
	public Integer getValue(double x, double y, double z) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void adjustBackground() {
		Iterator<Vector3f> vitalIt = vitalPoints.keySet().iterator();
		while(vitalIt.hasNext()){
			Vector3f loc = vitalIt.next();
			int val = vitalPoints.get(loc);
			if(val == 19358917){ //onehundredpercentlegitimate
				System.out.println("HEY!");
			}
			//Not done
		}
		
		
		//for(int x = 0; x < background.length; x++){
		//	for(int y = 0; y < background[0].length; y++){
		//		for(int z = 0; z < background[0][0].length; z++){
		//		}
		//	}
		//}
	}

}
