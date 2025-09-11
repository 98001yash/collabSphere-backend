package com.company.collabSphere_backend.utils;



import org.web3j.codegen.SolidityFunctionWrapperGenerator;

import java.io.File;

public class Web3jContractGenerator {

    public static void main(String[] args) throws Exception {
        // Path to ABI and BIN files
        String binPath = "src/main/resources/contracts/CollabSphereBadge.bin";
        String abiPath = "src/main/resources/contracts/CollabSphereBadge.abi";

        // Output package for generated Java class
        String packageName = "com.collabsphere.contracts";

        // Directory where the generated Java class will be placed
        String outputDir = "src/main/java";

        SolidityFunctionWrapperGenerator.main(new String[]{
                "-b", binPath,
                "-a", abiPath,
                "-p", packageName,
                "-o", outputDir
        });

        System.out.println("✅ Web3j wrapper generated successfully!");
    }
}
