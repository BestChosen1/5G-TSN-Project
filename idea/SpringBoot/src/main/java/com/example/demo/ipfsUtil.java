package com.example.demo;

import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import io.ipfs.multihash.Multihash;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ipfsUtil {

    private IPFS ipfs;

    public ipfsUtil() {
        ipfs = new IPFS("127.0.0.1", 5001);
    }

    public String uploadToIpfs(String filePath) throws IOException {
        NamedStreamable.FileWrapper file = new NamedStreamable.FileWrapper(new File(filePath));

        MerkleNode addResult = ipfs.add(file).get(0);
        return addResult.hash.toString();
    }

    public String uploadToIpfs(byte[] data) throws IOException {
        NamedStreamable.ByteArrayWrapper file = new NamedStreamable.ByteArrayWrapper(data);
        MerkleNode addResult = ipfs.add(file).get(0);
        return addResult.hash.toString();
    }

    public byte[] downFromIpfs(String hash) {
        byte[] data = null;
        try {
            data = ipfs.cat(Multihash.fromBase58(hash));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public void downFromIpfs(String hash, String destFile) {
        byte[] data = null;
        try {
            data = ipfs.cat(Multihash.fromBase58(hash));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (data != null && data.length > 0) {
            File file = new File(destFile);
            if (file.exists()) {
                file.delete();
            }
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(file);
                fos.write(data);
                fos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
