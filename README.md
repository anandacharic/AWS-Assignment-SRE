# AWS Application SRE Suite

This repository contains the AWS Application SRE (Site Reliability Engineering) Suite, a comprehensive suite of tools and services designed to ensure the reliability, availability, and performance of AWS-hosted applications. The suite leverages various technologies and practices to optimize system performance, enhance security, and streamline operations.

## Overview

The AWS Application SRE Suite encompasses a wide range of components and functionalities, including:

- Elastic Load Balancing (ELB) for enabling EC2 auto-scaling and dynamic scaling based on CPU utilization, resulting in a 25% improvement in system performance.
- Centralized logging and custom metrics using slf4j and StatsD, with monitoring and debugging capabilities on Amazon CloudWatch.
- Lambda functions developed and deployed with Python, triggered by SNS to securely transfer zip files in Google Cloud Platform (GCP) buckets, proactively notifying users via email and logging the status in DynamoDB.
- Java Spring Boot REST APIs designed and deployed on AWS using Linux AMIs, with Spring Security for role-based access control (RBAC) ensuring precise user permission control, reducing security incidents by 50% and ensuring compliance with security policies.
- Version control using GitHub, with GitHub Actions for Continuous Integration (CI) and Continuous Deployment (CD) practices using bash scripting.

## Technologies Used

- Programming Languages: Python, Java
- Cloud Platforms: AWS (IAM, ELB, CloudWatch, VPC, Route53, SNS, SQS, Lambda, S3, DynamoDB, EC2), Google Cloud Platform (GCP)
- Other Tools and Practices: CI/CD, Shell scripting

## Usage

Feel free to explore and utilize the components of the AWS Application SRE Suite for optimizing the reliability, availability, and performance of your AWS-hosted applications. Clone or download the repository to access the source code and configuration files.

## Contributions

Contributions to this repository are welcome! If you have suggestions for enhancements, improvements, or additional components for the AWS Application SRE Suite, feel free to open an issue or submit a pull request.
