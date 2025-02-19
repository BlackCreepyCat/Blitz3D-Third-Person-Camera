Graphics3D(800,600,32,2)
SetBuffer(BackBuffer())


ThisCamera							=CreateCamera()
ThisLight							=CreateLight(1)

ThisPlane							=CreatePlane()
EntityColor(ThisPlane,120,120,120)

For ThisCubeCnt = 1 To 20
	ThisCube							=CreateCube()
	EntityColor(ThisCube, Rnd(10,255), Rnd(10,255), Rnd(10,255))
	PositionEntity(ThisCube, Rnd(-50.0,50.0), 1, Rnd(-50.0,50.0))
Next


ThisPlayer							=CreatePivot()
ThisPivot							=CreatePivot(ThisPlayer)
ThisModel							=CreateCone(8,1)
EntityColor(ThisModel,Rnd(100,255), Rnd(100,255), Rnd(100,255))
RotateMesh(ThisModel, 90,0,0)
ScaleMesh(ThisModel, .5,.5,1)
EntityParent(ThisCamera, ThisPivot)
MoveEntity(ThisCamera,0,0,-10)
MoveEntity(ThisPlayer,0,1,0)



While Not KeyDown(1)
	ThisMX								=(GraphicsWidth() / 2) - MouseX()
	ThisMY								=MouseY() - (GraphicsHeight() / 2)
	MoveMouse((GraphicsWidth() / 2) , (GraphicsHeight() / 2))
	
	If MouseDown(2) Then 
		ThisRX								=ThisRX + ThisMX
		ThisRY								=ThisRY + ThisMY
		If ThisRY >  89 Then ThisRY =  89
		If ThisRY < -89 Then ThisRY = -89
	EndIf
	RotateEntity(ThisPlayer,0,ThisRX,0)
	RotateEntity(ThisPivot,ThisRY,0,0)
	ThisSpeed#							=.5
	ThisVX#								=EntityX#(ThisPlayer,1)
	ThisVZ#								=EntityZ#(ThisPlayer,1)
	If KeyDown(200) Then MoveEntity(ThisPlayer,0,0, ThisSpeed)
	If KeyDown(208) Then MoveEntity(ThisPlayer,0,0,-ThisSpeed)
	If KeyDown(203) Then MoveEntity(ThisPlayer,-ThisSpeed,0,0)
	If KeyDown(205) Then MoveEntity(ThisPlayer, ThisSpeed,0,0)
	ThisVX#								=ThisVX - EntityX#(ThisPlayer,1)
	ThisVZ#								=ThisVZ - EntityZ#(ThisPlayer,1)
	If ThisVX > 0 Or ThisVZ <> 0 Then
		AlignToVector(ThisModel,-ThisVX,0,-ThisVZ,3,.3)
	EndIf
	PositionEntity(ThisModel, EntityX(ThisPlayer,1), EntityY(ThisPlayer,1), EntityZ(ThisPlayer,1))
	RenderWorld()
	Flip()
Wend
End
