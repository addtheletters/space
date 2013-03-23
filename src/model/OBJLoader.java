package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.lwjgl.util.vector.Vector3f;

public class OBJLoader {
	public static Model loadModel(File f) throws FileNotFoundException, IOException{
		BufferedReader reader = new BufferedReader(new FileReader(f));
		Model m = new Model();
		String line;
		while((line = reader.readLine()) != null){
			//parse the obj file
			if(line.startsWith("v ")){
				String[] tokens = line.split(" ");
				float x = Float.valueOf(tokens[1]);
				float y = Float.valueOf(tokens[2]);
				float z = Float.valueOf(tokens[3]);
				m.vertices.add(new Vector3f(x, y, z));
			}
			else if(line.startsWith("vn ")){
				String[] tokens = line.split(" ");
				float x = Float.valueOf(tokens[1]);
				float y = Float.valueOf(tokens[2]);
				float z = Float.valueOf(tokens[3]);
				m.normals.add(new Vector3f(x, y, z));	
			}
			else if(line.startsWith("f ")){
				String[] spaceTokens = line.split(" ");
				Vector3f vertexIndices = new Vector3f(Float.valueOf(spaceTokens[1].split("/")[0]), 
						Float.valueOf(spaceTokens[2].split("/")[0]), 
						Float.valueOf(spaceTokens[3].split("/")[0]));
				Vector3f normalIndices = new Vector3f(Float.valueOf(spaceTokens[1].split("/")[2]), 
						Float.valueOf(spaceTokens[2].split("/")[2]), 
						Float.valueOf(spaceTokens[3].split("/")[2]));
				m.faces.add(new Face(vertexIndices, normalIndices));
			}
			
		}
		reader.close();
		return m;
	}
}
