# ビルドステージ: MavenとOpenJDK 17のイメージを使用
FROM maven:3.8.5-openjdk-17 AS build
# 作業ディレクトリを/appに設定
WORKDIR /app
# pom.xmlをコピー（依存関係のダウンロードを高速化するため）
COPY pom.xml .
# ソースコードをコピー
COPY src ./src
# Spring Bootアプリケーションをパッケージ化（テストはスキップ）
RUN mvn clean package -DskipTests

# 最終的な実行ステージ: 軽量なOpenJDK 17イメージを使用
FROM openjdk:17-jdk-slim
# 作業ディレクトリを/appに設定
WORKDIR /app
# ビルドステージで作成されたJARファイルをコピー
# your-app.jarは、mvn packageで生成されるJARファイル名に合わせて調整してください
# 通常はtargetフォルダ内のjarファイルになります
# 例: COPY --from=build /app/target/battle-factory-search-app-0.0.1-SNAPSHOT.jar app.jar
COPY --from=build /app/target/*.jar app.jar
# Spring Bootのデフォルトポートである8080を公開
EXPOSE 8080
# アプリケーションを起動するコマンド
ENTRYPOINT ["java", "-jar", "app.jar"]