package com.koyama.base;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class UiTestSupport {

    protected static final Duration DEFAULT_EXPLICIT_WAIT = Duration.ofSeconds(10);
    protected static final Duration DEFAULT_DOWNLOAD_WAIT = Duration.ofSeconds(20);
    protected static final boolean HEADLESS_ENABLED = Boolean.parseBoolean(System.getProperty("headless", "false"));

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Path downloadDirectory;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        downloadDirectory = createDownloadDirectory();

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", createChromePreferences());
        options.addArguments("--disable-notifications");

        if (HEADLESS_ENABLED) {
            options.addArguments("--headless=new");
        }

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, DEFAULT_EXPLICIT_WAIT);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    public Path getDownloadDirectory() {
        return downloadDirectory;
    }

    protected Path waitForDownloadedFile(String fileName) {
        return waitForDownloadedFile(fileName, DEFAULT_DOWNLOAD_WAIT);
    }

    protected Path waitForDownloadedFile(String fileName, Duration timeout) {
        Path expectedFile = downloadDirectory.resolve(fileName);
        Path partialDownload = downloadDirectory.resolve(fileName + ".crdownload");
        Instant deadline = Instant.now().plus(timeout);

        while (Instant.now().isBefore(deadline)) {
            if (Files.exists(expectedFile) && !Files.exists(partialDownload)) {
                return expectedFile;
            }

            sleep(Duration.ofMillis(250));
        }

        throw new IllegalStateException(
            "The downloaded file was not found in time: " + expectedFile.toAbsolutePath()
        );
    }

    private Map<String, Object> createChromePreferences() {
        Map<String, Object> preferences = new HashMap<>();

        preferences.put("download.default_directory", downloadDirectory.toAbsolutePath().toString());
        preferences.put("download.prompt_for_download", false);
        preferences.put("download.directory_upgrade", true);
        preferences.put("plugins.always_open_pdf_externally", true);
        preferences.put("profile.default_content_settings.popups", 0);
        preferences.put("safebrowsing.enabled", true);

        return preferences;
    }

    private Path createDownloadDirectory() {
        Path directory = Paths.get("target", "downloads", UUID.randomUUID().toString()).toAbsolutePath();

        try {
            return Files.createDirectories(directory);
        } catch (IOException exception) {
            throw new IllegalStateException("Could not create the download directory for the test.", exception);
        }
    }

    private void sleep(Duration duration) {
        try {
            Thread.sleep(duration.toMillis());
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("The test execution was interrupted while waiting for a download.", exception);
        }
    }
}
