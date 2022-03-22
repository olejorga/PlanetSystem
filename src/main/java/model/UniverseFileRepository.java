package model;

public interface UniverseFileRepository {
    void readFile(String filePath);
    void writeFile(String filePath);
}
