# Acoustic Data Management Tool

**Acoustic Data Management Tool** is a Java-based application for processing and analyzing acoustic data. It provides functionalities to read acoustic data from a file, perform data analysis, and display the results.

## Table of Contents

- [Features](#features)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)
- [Acknowledgements](#acknowledgements)

## Features

- Read acoustic data from a file.
- Analyze data for a specific date.
- Display mean, median, and standard deviation for frequency, amplitude, and duration.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) installed
- Git installed (for cloning the repository)

### Installation

1. Clone the repository:

    ```bash
    git clone https://github.com/your-username/acoustic-data-management-tool.git
    ```

2. Navigate to the project directory:

    ```bash
    cd acoustic-data-management-tool
    ```

3. Build the project:

    ```bash
    javac -d out src/com/acousticdata/*.java src/com/acousticdata/analysis/*.java src/com/acousticdata/algorithms/*.java src/com/acousticdata/exceptions/*.java src/com/acousticdata/io/*.java src/com/acousticdata/userinterface/*.java
    ```

## Usage

1. Run the application:

    ```bash
    java -cp out com.acousticdata.AcousticDataProcessor
    ```

2. Enter the file path when prompted.

3. View the analysis results.

## Contributing

Contributions are welcome! Please follow the [Contribution Guidelines](CONTRIBUTING.md).

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgements

- Special thanks to contributors and the open-source community.
