package middleware.org.jsonpojo;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.jsonschema2pojo.SchemaMapper;

import com.sun.codemodel.JCodeModel;

public class Test {

	public static void main(String[] args) throws IOException {
		 JCodeModel codeModel = new JCodeModel();
        URL source = new URL("file:///F:\\Mrcubes\\Json\\UpdateUsersResSchema.schema");
        new SchemaMapper().generate(codeModel, "UpdateUsersRes", "middleware.org.models", source);
        codeModel.build(new File("F:\\MrcubesMainProject\\LoginUpdateGetAddDetails\\src\\main\\java"));
	}

}
