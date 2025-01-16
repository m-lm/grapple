package src.storage;

public class StorageFactory {
    // Factory pattern class for modular retrieval of storage method

    public static void getStorage(String type) {
        switch (type.toLowerCase()) {
            case "protobuf":
                //return new ProtobufStorage();
                break;
            case "json":
            default:
                //return new JsonStorage();
        }
    }
}
