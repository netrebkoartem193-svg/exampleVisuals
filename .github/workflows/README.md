# Visual Client files for Fabric 1.21.11

Положи файлы так:

- VisualClient.java → src/client/java/com/example/visualclient/VisualClient.java
- HudRenderer.java → src/client/java/com/example/visualclient/HudRenderer.java
- HitParticles.java → src/client/java/com/example/visualclient/HitParticles.java
- CustomCrosshair.java → src/client/java/com/example/visualclient/CustomCrosshair.java
- Fullbright.java → src/client/java/com/example/visualclient/Fullbright.java
- CrosshairMixin.java → src/client/java/com/example/visualclient/mixin/CrosshairMixin.java
- visualclient.client.mixins.json → src/client/resources/visualclient.client.mixins.json
- fabric.mod.json → src/main/resources/fabric.mod.json

После этого собери проект (./gradlew build).

Включённые визуалы:
- FPS / координаты / скорость
- Armor status
- Potion effects
- Target HUD
- Keystrokes
- Hit Particles
- Custom Crosshair
- Fullbright (нужно вызвать Fullbright.toggle() с бинда или команды)

Можно расширять дальше.
